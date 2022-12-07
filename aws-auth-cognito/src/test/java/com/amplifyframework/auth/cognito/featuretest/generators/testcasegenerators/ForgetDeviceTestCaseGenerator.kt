package com.amplifyframework.auth.cognito.featuretest.generators.testcasegenerators

import aws.sdk.kotlin.services.cognitoidentityprovider.model.NotAuthorizedException
import com.amplifyframework.auth.cognito.featuretest.API
import com.amplifyframework.auth.cognito.featuretest.AuthAPI
import com.amplifyframework.auth.cognito.featuretest.CognitoType
import com.amplifyframework.auth.cognito.featuretest.ExpectationShapes
import com.amplifyframework.auth.cognito.featuretest.FeatureTestCase
import com.amplifyframework.auth.cognito.featuretest.MockResponse
import com.amplifyframework.auth.cognito.featuretest.PreConditions
import com.amplifyframework.auth.cognito.featuretest.ResponseType
import com.amplifyframework.auth.cognito.featuretest.generators.SerializableProvider
import com.amplifyframework.auth.cognito.featuretest.generators.toJsonElement
import kotlinx.serialization.json.JsonObject

object ForgetDeviceTestCaseGenerator : SerializableProvider {
    private val mockCognitoResponse = MockResponse(
        CognitoType.CognitoIdentityProvider,
        "updateDeviceStatus",
        ResponseType.Success,
        JsonObject(emptyMap())
    )

    private val apiReturnValidation = ExpectationShapes.Amplify(
        AuthAPI.forgetDevice,
        ResponseType.Success,
        JsonObject(emptyMap()),
    )

    private val baseCase = FeatureTestCase(
        description = "Test that Cognito is called with given payload and returns successful data",
        preConditions = PreConditions(
            "authconfiguration.json",
            "SignedIn_SessionEstablished.json",
            mockedResponses = listOf(mockCognitoResponse)
        ),
        api = API(
            AuthAPI.forgetDevice,
            JsonObject(emptyMap()),
            JsonObject(emptyMap())
        ),
        validations = listOf(apiReturnValidation)
    )

    private val successCase: FeatureTestCase = baseCase.copy(
        description = "Nothing is returned when forget device succeeds",
        preConditions = baseCase.preConditions.copy(mockedResponses = listOf(mockCognitoResponse)),
        validations = baseCase.validations.plus(apiReturnValidation)
    )

    private val errorCase: FeatureTestCase
        get() {
            val errorResponse = NotAuthorizedException.invoke {}
            return baseCase.copy(
                description = "AuthException is thrown when forgetDevice API call fails",
                preConditions = baseCase.preConditions.copy(
                    mockedResponses = listOf(
                        MockResponse(
                            CognitoType.CognitoIdentityProvider,
                            "forgetDevice",
                            ResponseType.Failure,
                            errorResponse.toJsonElement()
                        )
                    )
                ),
                validations = listOf(
                    ExpectationShapes.Amplify(
                        AuthAPI.forgetDevice,
                        ResponseType.Failure,
                        com.amplifyframework.auth.exceptions.NotAuthorizedException(
                            cause = errorResponse
                        ).toJsonElement(),
                    )
                )
            )
        }

    override val serializables: List<Any> = listOf(baseCase, errorCase, successCase)
}
