package com.crow.domain

case class Barrage(mid: String, timeReal: Long, timeIndex: Long, content: String) extends Serializable
case class BarrageEvent(platform: String, action: String, liver: String, date: String, fileName: String, suffix: String = ".flv", barrages: List[Barrage], isSort: Boolean = false) extends Serializable
case class BarragePoint(var startTime: Long,var duration: Long, var pointScore: Int = 0, var barrageNum: Int = 0, var endTime: Long = 0, var barrages: List[String] = List()) extends Serializable
