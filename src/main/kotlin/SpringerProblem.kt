const val MAX_X = 8
const val MAX_Y = 8

data class Koordinate(val x: Int, var y: Int) {

    fun isValide() = x in 0 until (MAX_X-1) && y in 0 until (MAX_Y-1)

}

class SpringerProblem {
    var feld = arrayOf<Array<Int>>()
    var StartPosX: Int = 0
    var StartPosY: Int = 0
    var counter: Int = 0


    fun durchlaufen(x: Int, y: Int) {
        zugAusfuehren(x, y)
    }

    fun zugAusfuehrenRekusiv(x: Int, y: Int) : Boolean{
        if (zuegeMoeglich(x, y)) {
            counter++
            feld[StartPosX][StartPosY] = counter;
            arrayAusgabe()
            println("Durchlauf:" + counter)
            if(zugAusfuehrenRekusiv(StartPosX, StartPosY))
                return true;
        }

        return false

    }

    fun zugAusfuehren(x: Int, y: Int): Unit {
        if (zuegeMoeglich(x, y))
            feld[x][y] = counter;
    }

    fun zuegeMoeglich(x: Int, y: Int): Boolean {
        var paar: List<String>
        var zuege = zugListeErstellen(x, y)
        println("Mögliche Züge: $zuege")
        for (zug in zuege) {
            println("Ist feld x:" + zug.x + " y:" + zug.y + " frei")
            if (feld[zug.x][zug.y] == 0) {  //Mach wenn Feld frei und pos im feld
                StartPosX = zug.x
                StartPosY = zug.y
                println("Der Neue Start Punkt ist x: " + StartPosX + "y: " + StartPosY)
                return true
            }
        }
        return false
    }


    fun arrayAusgabe(): Unit {
        for (array in feld) {
            for (value in array) {
                print("$value ")
            }
            println()
        }
    }

    fun arrayFuellen(): Unit {
        for (i in 0..7) {
            var array = arrayOf<Int>()
            for (j in 0..7) {
                array += 0
            }
            feld += array
        }
    }

    fun zugListeErstellen(x: Int, y: Int): MutableList<Koordinate> {  //x y Aktueller Standort
        var listZuege = mutableListOf<Koordinate>()

        //Variabeln zur berechnung der Zuege
        listZuege += Koordinate(x + 2, y + 1)
        listZuege += Koordinate(x + 2, y - 1)
        listZuege += Koordinate(x - 2, y + 1)
        listZuege += Koordinate(x - 2, y - 1)
        listZuege += Koordinate(x + 1, y + 2)
        listZuege += Koordinate(x + 1, y - 2)
        listZuege += Koordinate(x - 1, y + 1)
        listZuege += Koordinate(x - 1, y - 2)

        listZuege.removeIf { !it.isValide() }

        return listZuege
    }

}