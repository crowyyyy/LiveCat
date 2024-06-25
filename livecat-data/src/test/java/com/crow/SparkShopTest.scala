
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Req1_HotCategoryTop10Analysis {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("RDDCreate")
    val sc = new SparkContext(sparkConf)
    // TODO 1.获取原始数据
    val actionRDD = sc.textFile("datas/user_visit_action.txt")
    // TODO 2.分别统计一个品类的点击数量，下单数量，支付数量
    // 2.1 品类的点击数量的统计
    // 保留点击数据，其他数据不要
    val clickActionRDD = actionRDD.filter(
      action => {
        val datas = action.split("_")
        datas(6) != "-1"
      }
    )
    //（品类ID，1 click）=> (品类ID，sum click)
    val clickRDD = clickActionRDD.map(
      action => {
        val datas = action.split("_")
        (datas(6), 1)
      }
    )
    // (品类ID，sum click)
    val totalClickRDD = clickRDD.reduceByKey(_ + _)

    // 2.2 品类的下单数量的统计
    // 保留下单数据，其他数据不要
    val orderActionRDD = actionRDD.filter(
      action => {
        val datas = action.split("_")
        datas(8) != "null"
      }
    )
    // （品类ID，1 order）=> (品类ID，sum order)
    val orderRDD = orderActionRDD.flatMap(
      action => {
        val datas = action.split("_")
        val categoryids = datas(8)
        val ids = categoryids.split(",")
        ids.map((_, 1))
      }
    )
    // (品类ID，sum order)
    val totalOrderRDD = orderRDD.reduceByKey(_ + _)

    // 2.3 品类的支付数量的统计
    val payActionRDD = actionRDD.filter(
      action => {
        val datas = action.split("_")
        datas(10) != "null"

      }
    )
    // （品类ID，1 pay）=> (品类ID，sum pay)
    val payRDD = payActionRDD.flatMap(
      action => {
        val datas = action.split("_")
        val categoryids = datas(10)
        val ids = categoryids.split(",")
        ids.map((_, 1))
      }
    )
    // (品类ID，sum pay)
    val totalPayRDD = payRDD.reduceByKey(_ + _)

    // TODO 3. 将统计结果进行排行（点击，下单，支付）
    // Scala可以将无关的数据封装为一个整体来使用，可以使用元组
    // 元组在scala中的排序方式，首先按照第一个元素排序，相同者，按照第二个元素排序，依此类推

    // (品类ID,(点击数量，下单数量，支付数量))
    val clickLeftJoinOrderRDD: RDD[(String, (Int, Option[Int]))] = totalClickRDD.leftOuterJoin(totalOrderRDD)
    val clickOrderPayRDD: RDD[(String, ((Int, Option[Int]), Option[Int]))] = clickLeftJoinOrderRDD.leftOuterJoin(totalPayRDD)

    // ((Int, Option[Int]), Option[Int]))
    // ((点击，下单)，支付)
    val wantRDD = clickOrderPayRDD.map {
      case (cid, ((click, optOrder), optPay)) => {
        val order = optOrder.getOrElse(0)
        val pay = optPay.getOrElse(0)
        (cid, ((click, order), pay))
      }
    }

    // TODO 4.  取前10名
    //根据(click, order), pay)排序，false是降序,take取前10
    val result: Array[(String, ((Int, Int), Int))] = wantRDD.sortBy(_._2, false).take(10)
    result.foreach(println)
    sc.stop()

  }

}


