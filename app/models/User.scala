package models

import play.api.db.slick.Config.driver.simple._
import org.mindrot.jbcrypt.BCrypt


case class User(user: String, password: String){
  val wes = new User("wes", "password")

  def passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(12))

  if (BCrypt.checkpw(password, passwordHash)) {
    println("Password matches")
  } else {
    println("No Match")
  }
}

case class DBUser(id: Option[Int]=None, user:String, password:String)

class UsersTable(tag: Tag) extends Table[DBUser](tag, "users") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def user = column[String]("user")
  def password = column[String]("password")


  def * = (id.?, user, password) <> (DBUser.tupled, DBUser.unapply _)

}






