package controllers

import akka.actor.Status.Success
import models.{Tasks, Task}
import play.api.Play
import play.api.libs.json.{JsPath, Reads, JsError}
import play.api.libs.functional.syntax._
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by natercio on 23/03/16.
  */
class TaskController extends Controller {

  implicit val locationReads: Reads[Task] = (
    (JsPath \ "id").read[Long] and
      (JsPath \ "title").read[String]
    )(Task.apply _)

  def index = Action.async { implicit request =>
    Tasks.all.map { users =>
      Ok(views.html.tasks(users))
    }
  }

  def create = Action(parse.json) { implicit request =>
    request.body.validate[Task].map {
      case Task(i, t) => {
        Tasks.add(new Task(i, t))
        Redirect(routes.TaskController.index)
      }
    }.recoverTotal {
      e => BadRequest("Detected error:"+ JsError.toFlatJson(e))
    }
  }

  def read = TODO

  def update = TODO

  def delete = TODO

}
