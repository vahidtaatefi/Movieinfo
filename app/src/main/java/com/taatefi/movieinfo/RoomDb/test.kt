
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

import androidx.room.PrimaryKey


@Entity(tableName = "users")
class User {
    @PrimaryKey(autoGenerate = true)
    var id = 0
        private set

    @ColumnInfo(name = "name")
    var name: String
        private set

    constructor(id: Int, name: String) {
        this.id = id
        this.name = name
    }

    @Ignore
    constructor(name: String) {
        this.name = name
    }

}