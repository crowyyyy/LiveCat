//package com.crow.core.spark
//import com.crow.core.data.score.ScoreStrategyFactory
//import com.crow.domain.{Barrage, BarrageEvent, BarragePoint}
//import org.apache.spark.sql.{DataFrame, Dataset, Encoders, SparkSession}
//
//
//object BarrageProcessor {
//  def main(args: Array[String]): Unit = {
//    val spark = SparkSession.builder()
//      .appName("BarrageProcessor")
//      .enableHiveSupport()
//      .getOrCreate()
//
//    import spark.implicits._
//
//    val Array(hiveTable, hiveResultTable, duration:=Long, splitType, scoreType, liver) = args
//    val eventDF = spark.sql(s"SELECT * FROM $hiveTable WHERE condition")
//    val barrages = loadBarragesFromHive(eventDF)
//
//
//    val scoreStrategy = ScoreStrategyFactory.getScoreStrategy(scoreType)
//
//    if (scoreType != null) {
//      val splitStrategy = new SplitStrategy(barrages, duration, scoreStrategy)
//      val split = splitStrategy.split()
//      if (split != null) {
//        saveBarragePointsToHive(split, "path")
//      }
//    }
//
//    spark.stop()
//  }
//
//  def loadBarragesFromHive(eventDF: DataFrame)(implicit spark: SparkSession): List[Barrage] = {
//    import spark.implicits._
//    eventDF.select("barrages").as[List[Barrage]].collect().toList.flatten
//  }
//
//
//  def saveBarragePointsToHive(points: List[BarragePoint], path: String)(implicit spark: SparkSession): Unit = {
//    import spark.implicits._
//    val pointsDS = spark.createDataset(points)
//    pointsDS.write.format("hive").mode("overwrite").saveAsTable("your_hive_table_name")
//  }
//
//}
