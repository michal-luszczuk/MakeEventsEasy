package com.luszczuk.makeeventseasy.flurry

import com.luszczuk.makeeventseasy.base.EventParameter
import com.luszczuk.makeeventseasy.firebase.EventParametersToJsonObjectConverter
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EventParametersToJsonObjectConverterTest {

    companion object {
        private const val EXAMPLE_PARAMETER_NAME_ONE = "parameterNameOne"
        private const val EXAMPLE_PARAMETER_NAME_TWO = "parameterNameTwo"
        private const val EXAMPLE_PARAMETER_NAME_THREE = "parameterNameThree"
        private const val EXAMPLE_PARAMETER_NAME_FOUR = "parameterNameFour"
        private const val EXAMPLE_PARAMETER_NAME_FIVE = "parameterNameFive"
        private const val EXAMPLE_PARAMETER_NAME_SIX = "parameterNameSix"

        private const val EXAMPLE_STRING_PARAMETER_VALUE = "parameterValueOne"
        private const val EXAMPLE_INT_PARAMETER_VALUE = 16
        private const val EXAMPLE_LONG_PARAMETER_VALUE = 17L
        private const val EXAMPLE_FLOAT_PARAMETER_VALUE = 34.0f
        private const val EXAMPLE_DOUBLE_PARAMETER_VALUE = 35.0
        private const val EXAMPLE_BOOLEAN_PARAMETER_VALUE = true
    }


    private lateinit var converter: EventParametersToJsonObjectConverter

    @Before
    fun setUp() {
        converter = EventParametersToJsonObjectConverter()
    }


    @Test
    fun `GIVEN event without parameters WHEN convert THEN empty map`() {
        //given
        val parameters = emptyList<EventParameter<String, *>>()

        //when
        val actualJsonObject = converter.convert(parameters)

        //then
        actualJsonObject.length() `should be equal to` 0
    }

    @Test
    fun `GIVEN multiple parameters of all types WHEN convert THEN map with string values`() {
        //given
        val parameters = listOf<EventParameter<String, *>>(
            EventParameter.StringEventParameter(
                EXAMPLE_PARAMETER_NAME_ONE,
                EXAMPLE_STRING_PARAMETER_VALUE
            ),
            EventParameter.BooleanEventParameter(
                EXAMPLE_PARAMETER_NAME_TWO,
                EXAMPLE_BOOLEAN_PARAMETER_VALUE
            ),
            EventParameter.IntEventParameter(
                EXAMPLE_PARAMETER_NAME_THREE,
                EXAMPLE_INT_PARAMETER_VALUE
            ),
            EventParameter.LongEventParameter(
                EXAMPLE_PARAMETER_NAME_FOUR,
                EXAMPLE_LONG_PARAMETER_VALUE
            ),
            EventParameter.FloatEventParameter(
                EXAMPLE_PARAMETER_NAME_FIVE,
                EXAMPLE_FLOAT_PARAMETER_VALUE
            ),
            EventParameter.DoubleEventParameter(
                EXAMPLE_PARAMETER_NAME_SIX,
                EXAMPLE_DOUBLE_PARAMETER_VALUE
            )
        )

        //when
        val actualJsonObject = converter.convert(parameters)

        //then
        actualJsonObject.let {
            it.length() `should be equal to` parameters.size
            it[EXAMPLE_PARAMETER_NAME_ONE] `should be equal to` EXAMPLE_STRING_PARAMETER_VALUE
            it[EXAMPLE_PARAMETER_NAME_TWO] `should be equal to` EXAMPLE_BOOLEAN_PARAMETER_VALUE
            it[EXAMPLE_PARAMETER_NAME_THREE] `should be equal to` EXAMPLE_INT_PARAMETER_VALUE
            it[EXAMPLE_PARAMETER_NAME_FOUR] `should be equal to` EXAMPLE_LONG_PARAMETER_VALUE
            it[EXAMPLE_PARAMETER_NAME_FIVE] `should be equal to` EXAMPLE_FLOAT_PARAMETER_VALUE.toDouble()
            it[EXAMPLE_PARAMETER_NAME_SIX] `should be equal to` EXAMPLE_DOUBLE_PARAMETER_VALUE
        }
    }
}