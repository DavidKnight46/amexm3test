package org.futurlab.amexexcerise.service

import org.apache.ibatis.session.SqlSession
import org.futurlab.amexexcerise.database.AmexDatabaseMapper
import org.futurlab.amexexcerise.exceptions.GiftCardIDNotFoundException
import org.futurlab.amexexcerise.models.GiftCardModelDBResponse
import org.futurlab.amexexcerise.models.GiftCardModelRequest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.Mockito.anyString
import org.mockito.junit.jupiter.MockitoExtension
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
class AMEXServiceImplTest{
    private val COMPANY_NAME: String = "SHINRA"
    private val VALUE : Int = 1;
    private val POINTS_COST : Int = 5;
    private val EXPECTED_TIMES_CALLED : Int = 1;
    private val EXPECTED_SIZE : Int = 1;
    private val ID : String = UUID.randomUUID().toString();

    private lateinit var underTest: AMEXServiceI

    @Mock
    lateinit var sqlSession: SqlSession

    @Mock
    lateinit var testMapper: AmexDatabaseMapper

    @BeforeEach
    fun init(){
        `when`(sqlSession.getMapper(AmexDatabaseMapper::class.java)).thenReturn(testMapper)
        underTest = AMEXServiceImpl(sqlSession);
    }

    @Test
    fun test_CreateAnGiftCard_Success(){
        val result = underTest.createAGiftCard(createGiftCardModelRequest())

        assertNotNull(result.uuid, "UUID is valid")
        verify(sqlSession, times(EXPECTED_TIMES_CALLED)).commit(true)
    }

    @Test
    fun test_GetAnGiftCard_Success(){
        `when`(testMapper.getAnGiftCard(ID)).thenReturn(
            createGiftCardModelDBResponse()
        )

        val result = underTest.getGiftCard(ID)

        assertNotNull(result)
        assertTrue(result.id == ID)
    }

    @Test
    fun test_GetAnGiftCardNotFound_Exception(){
        `when`(testMapper.getAnGiftCard(anyString())).thenReturn(null)

        assertFailsWith(GiftCardIDNotFoundException::class,
                        { underTest.getGiftCard(ID) } )
    }

    @Test
    fun test_DeleteAnGiftCard_Success(){
        `when`(testMapper.getAnGiftCard(anyString())).thenReturn(createGiftCardModelDBResponse())

        underTest.deleteAnGiftCard(ID)

        verify(testMapper, times(EXPECTED_TIMES_CALLED)).deleteAnGiftCard(ID)
    }

    @Test
    fun test_DeleteAnGiftCardNotFound_Exception(){
        `when`(testMapper.getAnGiftCard(anyString())).thenReturn(null)

        assertFailsWith(GiftCardIDNotFoundException::class,
            { underTest.deleteAnGiftCard(ID) } )
    }

    @Test
    fun test_GetAllGifts_Success(){
        `when`(testMapper.getAllGiftCards()).thenReturn(createListOfGiftCards())

        val giftCards = underTest.getAllGiftCards()

        assertEquals(giftCards.size, EXPECTED_SIZE)
    }

    @Test
    fun test_GetAllGiftsEmpty_Success(){
        `when`(testMapper.getAllGiftCards()).thenReturn(emptyList())

        val giftCards = underTest.getAllGiftCards()

        assertTrue(giftCards.isEmpty())
    }

    private fun createGiftCardModelRequest(): GiftCardModelRequest{
        return GiftCardModelRequest(COMPANY_NAME, VALUE, POINTS_COST)
    }

    private fun createGiftCardModelDBResponse() =
        GiftCardModelDBResponse(COMPANY_NAME, VALUE, POINTS_COST, ID)

    private fun createListOfGiftCards(): List<GiftCardModelDBResponse>
        = mutableListOf(createGiftCardModelDBResponse())

}