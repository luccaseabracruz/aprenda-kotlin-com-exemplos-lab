// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

import java.util.UUID

fun gerarIdUnico(): String {
    return UUID.randomUUID().toString()
}

enum class Nivel { INICIANTE, INTERMEDIARIO, AVANCADO }

data class Usuario (val nome: String, var idade: Int){
    val id: String = gerarIdUnico()
}

data class ConteudoEducacional(val nome: String, var duracao: Int = 60)

data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>, val nivel: Nivel) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(vararg usuarios: Usuario) {
        inscritos.addAll(usuarios)
    }
    
    fun formacaoInf(): String {
        return """
            |INFORMAÇÕES SOBRE A FORMAÇÃO
            |	Nome: $nome
            |	Nível: $nivel.toLowerCase()
            |	Conteudos: ${conteudos.map{ it.nome }.joinToString()}
            |	Inscritos: ${inscritos.map{ it.nome }.joinToString()}
        """.trimMargin()
    }
}

val Formacao.nomesInscritosDaFormacao: String
	get() = inscritos.map{ it.nome }.joinToString()

fun main() {
    val jonas: Usuario = Usuario("Jonas Moura", 19)
    val jarbas: Usuario = Usuario("Jarbas Johnson", 35)
    val lucileidi: Usuario = Usuario("Lucileidi", 58)
    
    val principios: ConteudoEducacional = ConteudoEducacional("Princípios da Linguagem Kotlin")
    val poo: ConteudoEducacional = ConteudoEducacional("Programação Orientada a objetos", 120)
    val desafio: ConteudoEducacional = ConteudoEducacional("Desafio Final")
    
    val desenvolvimentoAndroid: Formacao = Formacao("Desenvolvimento Android", listOf(principios, poo, desafio), Nivel.INICIANTE) 
    
    desenvolvimentoAndroid.matricular(jonas, jarbas, lucileidi)
    
    println(desenvolvimentoAndroid.formacaoInf())
}
