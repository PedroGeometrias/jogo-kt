package od.core

// esse cara é um selo pois ele compoe personagem derivados de Personagem
sealed class CharacterClass(val nome:String, val descricao:String){
    object Guerreiro: CharacterClass("Guerreiro","Combatente marcial.")
    object Clerigo  : CharacterClass("Clérigo"  ,"Devoto; magia divina.")
    object Mago     : CharacterClass("Mago"     ,"Arcano estudioso.")
    // tenho que adicionar as outras classes ainda...

    // crio uma lisa dos personagems
    companion object { val all = listOf(Guerreiro, Clerigo, Mago) }
}
