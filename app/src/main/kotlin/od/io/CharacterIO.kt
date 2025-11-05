package od.io

import od.core.*
import java.io.File
// gerador de JSON
object CharacterIO {
    fun saveJson(ch: Character, path: String) {
        File(path).parentFile?.mkdirs()
        File(path).writeText(toJson(ch))
    }

    private fun esc(s: String) = s.replace("\\", "\\\\").replace("\"", "\\\"")

    fun toJson(ch: Character): String {
        val r = ch.raca
        val infr = r.infravisao?.toString() ?: "null"
        val jp = ch.humanoJPEscolhida?.let { "\"${esc(it)}\"" } ?: "null"
        val A = ch.atributos
        return """
{
  "nome": "${esc(ch.nome)}",
  "classe": "${esc(ch.classe.nome)}",
  "raca": "${esc(r.nome)}",
  "movimento": ${r.movimento},
  "infravisao": $infr,
  "alinhamento": "${esc(r.alinhamento)}",
  "atributos": {
    "FOR": ${A.forca},
    "DES": ${A.destreza},
    "CON": ${A.constituicao},
    "INT": ${A.inteligencia},
    "SAB": ${A.sabedoria},
    "CAR": ${A.carisma}
  },
  "habilidadesRaciaisMask": ${ch.habilidadesRaciaisMask},
  "humanoJPEscolhida": $jp
}
""".trimIndent()
    }
}
