//package com.crow.core.spark
//
//import com.crow.core.data.score.ScoreStrategy
//import com.crow.domain.{Barrage, BarragePoint}
//
//import java.util.concurrent.ConcurrentHashMap
//import scala.collection.JavaConverters._
//
//class SplitStrategy(
//                     barrages: List[Barrage],
//                     duration: Long,
//                     scoreStrategy: ScoreStrategy
//                   ) {
//  def split(): List[BarragePoint] = {
//    if(barrages == null || barrages.isEmpty) {
//      return null
//    }
//
//    var index = 0
//    var sum = 0
//    val points = scala.collection.mutable.ListBuffer[BarragePoint]()
//    val bgStartTime = barrages.head.timeReal
//    val bgEndTime = barrages.last.timeReal
//    val sumTime = bgEndTime - bgStartTime
//    var count = (sumTime / duration).toInt
//    if(sumTime%duration ==0){
//      count+=0
//    }else{
//      count+=1
//    }
//
//    for(i <- 0 until count) {
//      points += BarragePoint(bgStartTime + i * duration, duration)
//    }
//
//    for(i <- barrages.indices) {
//      val point = points(index)
//      val texts = point.barrages
//      val startTime = bgStartTime + index * duration
//      val endTime = startTime + duration
//      val barrage = barrages(i)
//
//      if(barrage.timeReal > endTime) {
//        point.pointScore = scoreStrategy.score(texts.asJava)
//        point.barrageNum = sum
//        point.endTime = barrages(i - 1).timeReal
//        index += 1
//        sum = 0
//      } else {
//          point.barrages = point.barrages :+ barrage.content
//          if(sum == 0) {
//            point.startTime = barrage.timeReal
//          }
//          sum += 1
//          if(i == barrages.size - 1) {
//            point.pointScore = scoreStrategy.score(texts.asJava)
//            point.barrageNum = sum
//            point.endTime = barrages(i - 1).timeReal
//          }
//      }
//    }
//    points.toList
//  }
//
//}
//
