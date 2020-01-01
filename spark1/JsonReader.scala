import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.json4s._
import org.json4s.jackson.JsonMethods._


object JsonReader extends App {

  if (args.length == 0) {
    println("Please, provide target JSON filepath")
    System.exit(0)
  }
 // println(s"Hello, ${args(0)}")

  val conf  = new SparkConf().setAppName("JsonReader").setMaster("local")
  val sc = new SparkContext(conf)
  sc.setLogLevel("WARN")

  case class WineJson (
                        id: Option[Int] = None,
                        country: Option[String] = None,
                        points: Option[Int] = None,
                        price: Option[Double] = None,
                        title: Option[String] = None,
                        variety: Option[String] = None,
                        winery: Option[String] = None
                      )

  val jsonFile = sc.textFile(s"${args(0)}")

  implicit val json4sFormats = DefaultFormats

 // jsonFile.map{ row => parse(row).extract[WineJson]}.collect().take(10).foreach{ e => println(e.getClass + " " + e)}
  jsonFile.map{ row => parse(row).extract[WineJson]}.collect().foreach{ a =>
                                                                                  println(a.id.getOrElse(None) + " " +
                                                                                    a.country.getOrElse(None) + " " +
                                                                                  a.points.getOrElse(None) + " " +
                                                                                  a.price.getOrElse(None) + " " +
                                                                                  a.title.getOrElse(None) + " " +
                                                                                  a.variety.getOrElse(None) + " " +
                                                                                  a.winery.getOrElse(None))
  }

}

