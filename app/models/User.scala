package models

import play.api.db.slick.Config.driver.simple._



case class User(user: String, password: String)

case class DBUser(id: Option[Int]=None, user:String, password:String)



class UsersTable(tag: Tag) extends Table[DBUser](tag, "users") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def user = column[String]("user")
  def password = column[String]("password")


  def * = (id.?, user, password) <> (DBUser.tupled, DBUser.unapply _)

}


