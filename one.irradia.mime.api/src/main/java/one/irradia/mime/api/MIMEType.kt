package one.irradia.mime.api

import java.io.Serializable

/**
 * An RFC2045 MIME type.
 *
 * This type is serializable to allow passing values of this type between Android activities.
 * Absolutely no guaratees are made that serialized values will be readable by future versions
 * of the application.
 */

data class MIMEType(
  val type: String,
  val subtype: String,
  val parameters: Map<String, String>
) : Serializable {

  /**
   * The combined type and subtype (not including parameters)
   */

  val fullType: String = "${this.type}/${this.subtype}"

  override fun toString(): String {
    return this.fullType
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) {
      return true
    }
    if (this.javaClass != other?.javaClass) {
      return false
    }

    other as MIMEType
    if (this.type != other.type) {
      return false
    }
    if (this.subtype != other.subtype) {
      return false
    }

    return if (this.parameters.size == other.parameters.size) {
      for ((k0, v0) in this.parameters) {
        if (other.parameters[k0] != v0) {
          return false
        }
      }
      true
    } else {
      false
    }
  }

  override fun hashCode(): Int {
    var result = this.type.hashCode()
    result = 31 * result + this.subtype.hashCode()
    result = 31 * result + this.parameters.hashCode()
    return result
  }


}
