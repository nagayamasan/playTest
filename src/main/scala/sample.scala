import play.api.libs.json.Reads._
import play.api.libs.json._
import reactivemongo.api.commands.WriteResult
import reactivemongo.api.{DefaultDB, MongoConnection, MongoDriver}
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.global
import scala.util.{Failure, Success}


object sample {
  def main(args: Array[String]): Unit = {
//    println("Hello")
    writeDocument()(global)
  }

  def collection(implicit ec: ExecutionContext): Future[JSONCollection] = {
    val driver = new MongoDriver
    val connection: MongoConnection = driver.connection(List("localhost:27017"))



    connection.database("local") map { defaultDb: DefaultDB =>
      defaultDb.collection[JSONCollection]("licos"): JSONCollection
    }
  }

  def writeDocument()(implicit ec: ExecutionContext): Unit = {

    val jsonString = """{
                        "guid": "alkshdlkasjd-ioqweuoiquew-123132",
                        "title": "Hello-2019",
                        "year": 2016,
                        "action": "POST",
                        "start": "2016-12-20",
                        "stop": "2016-12-30"}"""


    val document: JsObject = Json.parse(jsonString).as[JsObject]
    val future: Future[WriteResult] = {
      collection.map {
        _.insert.one(document)
      }
    }.flatten
    future.onComplete {
      case Failure(e) => throw e
      case Success(result) =>
        println("successfully inserted document with result = " + result)
    }
  }


}
