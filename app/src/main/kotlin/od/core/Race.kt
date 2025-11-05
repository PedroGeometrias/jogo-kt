package od.core
import od.util.RaceAbility

// Raca
sealed class Race(
    val nome:String,
    val movimento:Int,        // metros
    val infravisao:Int?,      // metros, null = não possui
    val alinhamento:String,
    val habilidadesMask:Int
){
    object Humano  : Race("Humano",  9, null, "qualquer",
        RaceAbility.APRENDIZADO or RaceAbility.ADAPTABILIDADE)

    object Elfo    : Race("Elfo",    9, 18,  "neutro",
        RaceAbility.PERCEPCAO_SECRETA or RaceAbility.GRACIOSOS or
        RaceAbility.ARCO_RACIAL or RaceAbility.IMUNE_SONO_PARALISIA)

    object Anao    : Race("Anão",    6, 18,  "ordem",
        RaceAbility.MINERADORES or RaceAbility.VIGOROSO or
        RaceAbility.ARMAS_GRANDES_TRATAMENTO or RaceAbility.INIMIGOS_ORC_OGRO_HOBG)

    object Halfling: Race("Halfling",6, null,"neutro",
        RaceAbility.FURTIVOS or RaceAbility.DESTEMIDOS or
        RaceAbility.BONS_DE_MIRA or RaceAbility.PEQUENOS)

    companion object { val all = listOf(Humano, Elfo, Anao, Halfling) }
}
