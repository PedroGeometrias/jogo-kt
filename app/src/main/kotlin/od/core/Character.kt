package od.core

// esse cara e salvo no Json
data class Character(
    val nome:String,
    val atributos: Attributes,
    val raca: Race,
    val classe: CharacterClass,
    val humanoJPEscolhida:String?,
    val habilidadesRaciaisMask:Int
)
