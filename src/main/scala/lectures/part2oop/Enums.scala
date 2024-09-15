package lectures.part2oop

object Enums extends App {

  enum  Permissions {
    case READ, WRITE, EXECUTE, NONE

    // allows field / method
    def openDocument(): Unit = {
      if (this == READ) println("Opening document...") else println("reading not allowed")
    }
    def me: Permissions = this
  }
  val somePermissions: Permissions = Permissions.READ
  somePermissions.openDocument()  // Opening document...
  println(somePermissions)        // READ
  println(somePermissions.me)     // READ
  println(Permissions.valueOf("WRITE"))  // WRITE
  println(Permissions.values.mkString("Array(", ", ", ")")) // Array(READ, WRITE, EXECUTE, NONE)

  // Constructor Args
  enum PermissionsWithBits(val bits: Int) {
    case READ extends PermissionsWithBits(4)    // 100
    case WRITE extends PermissionsWithBits(2)   // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0)    // 000
  }
  // Companion Object
  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits =
      bits match
        case 4 => READ
        case 2 => WRITE
        case 1 => EXECUTE
        case _ => NONE
  }
  val permissionsWithBits1 = PermissionsWithBits.EXECUTE
  println(permissionsWithBits1)
  println(permissionsWithBits1.ordinal) // 2
  println(permissionsWithBits1.bits)    // 1
  
}
