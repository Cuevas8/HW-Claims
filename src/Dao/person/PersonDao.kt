package org.csuf.cspc411.Dao.person

import org.csuf.cspc411.Dao.Dao
import org.csuf.cspc411.Dao.Database

class PersonDao : Dao() {

    fun addPerson(pObj : Person) {
        // 1. Get db connection
        val conn = Database.getInstance()?.getDbConnection()

        // 2. prepare the sql statement
        sqlStmt = "insert into person (first_name, last_name, ssn) values ('${pObj.firstName}', '${pObj.lastName}', '${pObj.ssn}')"

        // 3. submit the sql statement
        conn?.exec(sqlStmt)
    }

    fun getAll() : List<Person> {
        // 1. Get db connection
        val conn = Database.getInstance()?.getDbConnection()

        // 2. prepare the sql statement
        sqlStmt = "select first_name, last_name, ssn from person"

        // 3. submit the sql statement
        var personList : MutableList<Person> = mutableListOf()
        val st = conn?.prepare(sqlStmt)

        // 4. Convert into Kotlin object format
        while (st?.step()!!) {
            val fn = st.columnString(0)
            val ln = st.columnString(1)
            val ssn = st.columnString(2)
            personList.add(Person(fn, ln, ssn))
        }
        return personList
    }
}