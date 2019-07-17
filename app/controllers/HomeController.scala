package controllers

import javax.inject._
import play.api.Environment
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(
  cc: ControllerComponents,
  env: Environment
) () extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.landingPage())
  }

  def getCounts: Action[AnyContent] = Action.async {
    Future {
      val stream = env.classLoader.getResourceAsStream("public/test.json")
      val json = try {  Json.parse(stream) } finally { stream.close() }
      Ok(json)
    }
  }
}
