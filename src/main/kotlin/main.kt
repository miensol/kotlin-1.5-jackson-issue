import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

inline fun <reified T> jsonList(mapper: ObjectMapper, content: String) =
    json(mapper, content, object : TypeReference<List<T>>() {})

fun <T> json(
    mapper: ObjectMapper,
    content: String,
    typeReference: TypeReference<T>
): T {
    return mapper.readValue(content, typeReference)
}

fun main() {
    val mapper = ObjectMapper().registerKotlinModule()

    val listOfMaps = jsonList<Map<String, Any>>(mapper, """[{"someKey": "someValue"}]""")

    println(listOfMaps)
}