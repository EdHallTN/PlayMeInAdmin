package controllers

import models.{DBUser, User, _}
import org.joda.time.DateTime
import play.api._
import play.api.db.slick._
import play.api.db.slick.Config.driver.simple._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import play.api.Play.current
import play.api.mvc.BodyParsers._


import scala.concurrent.Future

object Application extends Controller {

  //create an instance of the table
  val users = TableQuery[UsersTable] //see a way to architect your app in the computers-database-slick sample

  //JSON read/write macro
  def index = Action { implicit rs =>
    val u = rs.session.isEmpty
    u match {
      case true => Ok(views.html.index.render())
      case false => Ok(views.html.upload.render())
    }
  }

  val userForm = Form(
    mapping(
      "name" -> text(),
      "password" -> text()
    )(User.apply)(User.unapply)
  )

  def login = Action { implicit rs =>
    val user = userForm.bindFromRequest.get
    isValidUser(user) match {
      case true => Redirect(routes.Application.index).withSession("user" -> user.name)
      case false => Redirect(routes.Application.index)
    }
  }

  def logout = Action { implicit rs =>
    rs.session - "user"

    Redirect(routes.Application.index)
  }

  def userInsert = DBAction { implicit rs =>
    val user = userForm.bindFromRequest.get
    val dbUser = new DBUser(None, user.name, user.password)
    users.insert(dbUser)

    Redirect(routes.Application.index).withSession("user" -> user.name)
  }

  def isValidUser(user: User) = {
    def isValidUser(user: User) : Boolean = {
      val t = (users.findBy(name => name == user.name)).extract == true
      println(t)
      t
    }
  }

}
