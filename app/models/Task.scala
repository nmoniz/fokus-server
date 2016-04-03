package models

import play.api.Play
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json._
import play.api.libs.functional.syntax._

    import slick.driver.H2Driver.api._
import slick.driver.JdbcProfile
import slick.util.GlobalConfig

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by natercio on 23/03/16.
  */

case class Task(id: Long, title: String)

class TaskTable(tag: Tag) extends Table[Task](tag, "task") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc) // This is the primary key column
  def title = column[String]("title")

  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, title) <> (Task.tupled, Task.unapply)
}

object Tasks {

  val form = Form(
    "task" -> tuple(
      "id" -> ignored(0L),
      "title" -> nonEmptyText
    )
  )

  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  val tasks = TableQuery[TaskTable]

  def all: Future[Seq[Task]] = {
    val db = Database.forConfig("slick.dbs.default.db")
    db.run(tasks.result)
    //db.run(tasks.schema.create)
  }

  def add(task: Task):Future[String] = {
    dbConfig.db.run(tasks += task).map(res => "Task successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }

}