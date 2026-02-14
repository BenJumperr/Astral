package xyz.aeroitems.proxy.velocity.network.codec

import com.google.gson.*
import xyz.aeroitems.proxy.velocity.network.packet.Packet
import xyz.aeroitems.proxy.velocity.network.packet.PacketRegistry
import java.lang.reflect.Type

object PacketCodec {

    fun gson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Packet::class.java, Adapter())
            .create()
    }

    private class Adapter : JsonSerializer<Packet>, JsonDeserializer<Packet> {

        override fun serialize(
            src: Packet,
            typeOfSrc: Type,
            context: JsonSerializationContext
        ): JsonElement {
            val obj = JsonObject()
            obj.addProperty("type", PacketRegistry.idOf(src.javaClass))
            obj.add("data", context.serialize(src))
            return obj
        }

        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): Packet {
            val obj = json.asJsonObject
            val id = obj["type"].asString
            val clazz = PacketRegistry.classOf(id)
                ?: error("Unknown packet type: $id")

            return context.deserialize(obj["data"], clazz)
        }
    }
}
