package models

import play.api.db.slick.Config.driver.simple._
import controllers.Application.users


case class User(name: String, password: String)

case class DBUser(id: Option[Int]=None, name: String, password: String)



class UsersTable(tag: Tag) extends Table[DBUser](tag, "users") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def password = column[String]("password")


  def * = (id.?, name, password) <> (DBUser.tupled, DBUser.unapply _)

}


