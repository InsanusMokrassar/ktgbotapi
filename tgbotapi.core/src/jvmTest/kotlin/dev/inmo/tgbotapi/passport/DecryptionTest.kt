package dev.inmo.tgbotapi.passport

import dev.inmo.micro_utils.crypto.decodeBase64
import dev.inmo.tgbotapi.utils.passport.Decryptor
import dev.inmo.tgbotapi.utils.passport.doWithDecryptor
import kotlin.test.Test
import kotlin.test.assertEquals

//expect val privateKey: String
//expect val encryptedLoremIpsum: EncryptedAndBase64EncodedData
val privateKey: String = """-----BEGIN PRIVATE KEY-----
MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCaaMFkmMBi6Vl5
vzy7wQjoZNLN+e9e6Lb/WBekJxfbNMt+EN9Zv7xT9xzfY68DX7v0uGoLcAqS5Mh5
8ELiJbzg4O8dbxkggt0G+kxcbT+a3ofm1Aqhr+LkpsFh5qbLqbAHSQJAF0HPqrAc
5c6MoQVTZlWGU/j3enCcUFo7Jpsi8La6MqYboF5xGwGEZ9tOA6rMDSekr5Rdm637
D/w4h284UX7VnqPdpZ/943VK2qvmPjm8HkFSxkSAB8RHlFyjazzm9FNOcVRGLynv
/cq7mWgyxt5nqiCG7w+lFNAFNQNvAO0rfeiTJbEzubquu4Mg31QrvLIuz+fUbsGk
ipfpN1czAgMBAAECggEAPlAoO8CpY0FoqolSqTKttZt6t0U2JMclksaqQ8TDC+Oy
e52zhTSre/ct37kK2AG6iHgj05nTqpRJk2wykbFJGDeuR+Kd8VDeggJg7qvoD0fe
8HiCEd45Yq0pPaknhulj8Iy2K8c29+eaSw8y2+3fiFi0CxG4V6dB6tNClrxtvxtl
IE85Utzmk4PLTCs010zxNVm+FwrvqlpjjhspljzleUuWE1KuldO9tNSlwUUfBr4i
BJJn4jDkQMloXoM/ArZEp7mn57N9+ZuEgVjRMCpRGEAbQI+aLCznBB1kCOY7WL8S
CwkP++SvcptaUpOjoXZZyuGU4CgBnu0+40ORbNchgQKBgQDLIbB9+WHDcZAn1g6M
8jaNDRU4HGu5vJSajFQK8Uaczg87hAxmk6R1GF0LOyoadaVmD5ax93mQxWSE/4hT
wyAPaQOFl7A/i1k/Dx/kURjRwF1sLWDFdMo/8t5bXumzrzb1bHYnHPS7S0lBAU+X
SrbIw+hv69aKawcQqdd54S1OBwKBgQDCmMarwMVn4xnOh+DOuLS4IQzPT84WgzH+
wKw55hZGMqZ4S3JRVv256daweEbKAeR5NCccPmir0H86jwUW6WFAtxqfPWF4piVi
UD6DrUt0gWwzMGy3kbAp0hLBtsQRp/41mKuAXkvMVQ2ysInn6lmHhZvjduK8lvKh
fMRDMjxidQKBgFiciK5blI86wgTutwg7PRrI40HH/CJZJoZIwvzHBeOvbCutTe+N
ZoeCKkyU8af7PDzKfhWCfHBv+4qdIi5QB3NRfyzO4B7IPhVpFqN10RrnDJn9LaLV
cMj2vJMlU1OEErh7KQuk8QmnLPyDguHfwN7Rv1rbiYp2Z+2X+Zx8Y1QPAoGBAJIf
nq/CJXoJMou/xLP2Rt4tEy1pQ9vr0FL341vmxrsXtaGHJeSmagh861XAO4fdO+83
llbDFl5ORft3Ad9eiETMOhVxRgwO1uuoTgkazBpERTd7GWgO4jXFJYiI8VpAx8b/
SWkvZcOd6pdPsX6Qn4IAdjqsPz5WKwPQaJ/8zRMxAoGAGaOuyXaFZNl8IeTGYMDL
OEg9BHa+Du8jSv0vL/fCN2s/2TcvSa9uEnv8KHz59JHevvxoDNbQYlN8Ge8adFv4
xZYQ3h/qh2ohnTUfrG4hcFUVBuv5HBdwgWhVFm5v98KQsqVzGfKorN2DemEv3/au
PFGkuu4lCPeDqu5u0dBojoA=
-----END PRIVATE KEY-----"""
val encryptedLoremIpsum = "KeuPVezTbMW4MvOGJFTAt34PRaL9iepGb1g4QEi5EQYjKXeXo7RytFOCVx6pW9O/uoMWhl+cX32kNqJVWyK7a22kuhnNT2+aiXqEpuh+madx+LK1zUvGDR1A1Mrf3fFOugcnadCQKICiqvl9cFdqsBkJQOFEs9qj5wiu1F57kekuHLWI7ZnHOojkRNq7l1aCHL0DxcLCfZW5CtWAi8g/zUE5WnGd+vUZ+hqc1vnehDul8JE8YUQbAiIxetzba9XoWouTHYZRZcNCztDbrRBYnq2UCcI5adEwQ3VNcES0lIjRuwn1BBWpvk7VOjqh+4c2tSebDX5AkqrO8XwQYwo8OwvZF+hUXFRK6QLHd4B1JQIdygCaEODG0X353upiEKJXDBqv/ZMXR9KqYZBZEfu48ZM/J6heNjVSOP4TSDrXywJgIOieu8mrQ4d7Or7Twnsu/B+bqS37PvVlfU4wHLl7ehXFj4Kusat6cIrb0R4F/Y3fL4+FcfEsk4ioEAndYBFrc1S11vo/TkcuFnXtqrr181gC5JD5LtsUH6sst36vE/JdL/UwTGqhu+rDUcgvr7FiunkasWBnzCtFs58JJrRycUKPzyKINS16GkY8yYtp7xJXBGPYOFM+J7npcKW7P41j1KceDaUArjph2yIELEXCr8qUZzWXZdrw96Te8gLi99c="


class DecryptionTest {
    val inputText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

    @Test
    fun testThatDecryptionIsWorkingCorrectly() {
        doWithDecryptor(privateKey) {
            val decrypted = encryptedLoremIpsum.decodeBase64().decrypt().decodeToString()
            assertEquals(inputText, decrypted)
        }
    }
}
