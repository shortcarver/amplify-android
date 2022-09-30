package featureTest.utilities

import com.amplifyframework.auth.AuthUserAttribute
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthResetPasswordOptions
import com.amplifyframework.auth.options.AuthSignOutOptions
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.testutils.featuretest.auth.AuthAPI
import com.amplifyframework.testutils.featuretest.auth.AuthAPI.resetPassword
import com.amplifyframework.testutils.featuretest.auth.AuthAPI.signOut
import com.amplifyframework.testutils.featuretest.auth.AuthAPI.signUp
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull

/**
 * Factory to create specialized options object for the top level APIs
 */
object AuthOptionsFactory {
    fun create(apiName: AuthAPI, optionsData: JsonObject): Any? = when (apiName) {
        resetPassword -> AuthResetPasswordOptions.defaults()
        signUp -> getSignUpOptions(optionsData)
        signOut -> getSignOutOptions(optionsData)
        else -> throw Error("Options for $apiName is not defined!")
    }

    private fun getSignUpOptions(optionsData: JsonObject): AuthSignUpOptions =
        AuthSignUpOptions.builder().userAttributes(
            (optionsData["userAttributes"] as Map<String, String>).map {
                AuthUserAttribute(AuthUserAttributeKey.custom(it.key), (it.value as JsonPrimitive).content)
            }
        ).build()

    private fun getSignOutOptions(optionsData: JsonObject): AuthSignOutOptions {
        val globalSignOutData = (optionsData["globalSignOut"] as JsonPrimitive).booleanOrNull ?: false
        return AuthSignOutOptions.builder()
            .globalSignOut(globalSignOutData)
            .build()
    }
}
