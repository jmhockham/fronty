package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future, Promise}
import play.api.Environment
import play.api.libs.json.Json

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(
  cc: ControllerComponents,
  env: Environment
) (implicit exec: ExecutionContext) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.landingPage())
  }

  def getCounts(): Action[AnyContent] = Action.async {
    Future {
      val stream = env.classLoader.getResourceAsStream("")
      val json = try {  Json.parse(stream) } finally { stream.close() }
      Ok(json)
    }
  }
}
