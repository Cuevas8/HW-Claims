package org.csuf.cspc411.Dao.claim

import org.csuf.cspc411.Dao.Dao
import org.csuf.cspc411.Dao.Database
import org.csuf.cspc411.Dao.person.Claim

//import org.csuf.cspc411.Dao.person.Claim


class ClaimDao : Dao() {

    fun addClaim(claimObj: Claim) {
        // 1. Get db connection
        val conn = Database.getInstance()?.getDbConnection()

        // 2. prepare the sql statement
        sqlStmt = "insert into claims (id, title, date, isSolved) values ('${claimObj.id}', '${claimObj.title}', '${claimObj.date}', '${claimObj.isSolved}')"

        // 3. submit the sql statement
        conn?.exec(sqlStmt)
    }

    fun getAll() : List<Claim> {
        // 1. Get db connection
        val conn = Database.getInstance()?.getDbConnection()

        // 2. prepare the sql statement
        sqlStmt = "select id, title, date, isSolved from claims"

        // 3. submit the sql statement
        var claimList : MutableList<Claim> = mutableListOf()
        val st = conn?.prepare(sqlStmt)

        // 4. Convert into Kotlin object format
        while (st?.step()!!) {
            val id = st.columnString(0)
            val title = st.columnString(1)
            val date = st.columnString(2)
            val isSolvedString = st.columnString(3)

            claimList.add(Claim(id, title, date, isSolvedString.toBoolean()))
        }

        return claimList
    }
}