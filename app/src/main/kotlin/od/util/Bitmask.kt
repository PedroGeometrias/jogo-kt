package od.util

object RaceAbility {
    const val APRENDIZADO                 = 1 shl 0   // Humano exemplo 0000 0000 0000 0001
    const val ADAPTABILIDADE              = 1 shl 1   // Humano exemplo 0000 0000 0000 0010
    const val PERCEPCAO_SECRETA           = 1 shl 2   // Elfo
    const val GRACIOSOS                   = 1 shl 3   // Elfo
    const val ARCO_RACIAL                 = 1 shl 4   // Elfo
    const val IMUNE_SONO_PARALISIA        = 1 shl 5   // Elfo
    const val MINERADORES                 = 1 shl 6   // Anão
    const val VIGOROSO                    = 1 shl 7   // Anão
    const val ARMAS_GRANDES_TRATAMENTO    = 1 shl 8   // Anão (tratamento racial p/ armas)
    const val INIMIGOS_ORC_OGRO_HOBG      = 1 shl 9   // Anão
    const val FURTIVOS                    = 1 shl 10  // Halfling
    const val DESTEMIDOS                  = 1 shl 11  // Halfling
    const val BONS_DE_MIRA                = 1 shl 12  // Halfling
    const val PEQUENOS                    = 1 shl 13  // Halfling
}

// aqui eu verifico se o bit marcado existe!!!!
fun Int.hasFlag(flag:Int) = (this and flag) != 0

// mapeio o bit mask para uma string, usavel para humanos ou o projeto
object RaceAbilityNames {
    // assim eu consigo acessar nas listas geradas de objetos
    val map = linkedMapOf(
        RaceAbility.APRENDIZADO to "Aprendizado",
        RaceAbility.ADAPTABILIDADE to "Adaptabilidade",
        RaceAbility.PERCEPCAO_SECRETA to "Percepção Natural",
        RaceAbility.GRACIOSOS to "Graciosos",
        RaceAbility.ARCO_RACIAL to "Arma Racial (arcos)",
        RaceAbility.IMUNE_SONO_PARALISIA to "Imune a sono/paralisia de Ghoul",
        RaceAbility.MINERADORES to "Mineradores",
        RaceAbility.VIGOROSO to "Vigoroso (JPC +1)",
        RaceAbility.ARMAS_GRANDES_TRATAMENTO to "Armas grandes (tratamento racial)",
        RaceAbility.INIMIGOS_ORC_OGRO_HOBG to "Inimigos: orcs/ogros/hobgoblins (ataque fácil)",
        RaceAbility.FURTIVOS to "Furtivos",
        RaceAbility.DESTEMIDOS to "Destemidos (JPS +1)",
        RaceAbility.BONS_DE_MIRA to "Bons de Mira (arremesso é ataque fácil)",
        RaceAbility.PEQUENOS to "Pequenos (difícil acertar com criaturas grandes+)"
    )
    fun decode(mask:Int) = map.filterKeys { mask.hasFlag(it) }.values.toList()
}
