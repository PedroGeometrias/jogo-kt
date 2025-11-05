package od.core

// data class serve apenas para segurar dados
data class Attributes(
    var forca:Int,
    var destreza:Int,
    var constituicao:Int,
    var inteligencia:Int,
    var sabedoria:Int,
    var carisma:Int
){
    // override em cima do toString que mostra a seguinte STRING
    // FOR=12 DES=14 CON=13 INT=10 SAB=8 CAR=11
    override fun toString(): String =
        "FOR=$forca DES=$destreza CON=$constituicao INT=$inteligencia SAB=$sabedoria CAR=$carisma"

    // lista ESTATIVA com os nomes dos atributos
    companion object {
        val names = listOf("Força","Destreza","Constituição","Inteligência","Sabedoria","Carisma")
    }

    // atributo opera como uma funcao ou lista, podendo ser assesado por um index
    operator fun get(idx:Int): Int = when(idx){
        0->forca; 1->destreza; 2->constituicao; 3->inteligencia; 4->sabedoria; 5->carisma
        else -> error("idx")
    }
    operator fun set(idx:Int, v:Int){ when(idx){
        0->forca=v; 1->destreza=v; 2->constituicao=v; 3->inteligencia=v; 4->sabedoria=v; 5->carisma=v
        else -> error("idx")
    } }
}
