package org.futurlab.amexexcerise.service

import lombok.extern.slf4j.Slf4j
import org.apache.ibatis.session.SqlSession
import org.futurlab.amexexcerise.database.AmexDatabaseMapper
import org.futurlab.amexexcerise.exceptions.GiftCardIDNotFoundException
import org.futurlab.amexexcerise.models.GiftCardModelDBResponse
import org.futurlab.amexexcerise.models.GiftCardModelRequest
import org.futurlab.amexexcerise.models.GiftCardModelResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Slf4j
@Service
class AMEXServiceImpl(
    private val createSQLSession: SqlSession
) : AMEXServiceI {
    private val logger = LoggerFactory.getLogger(this::class.java)

    private var mapper : AmexDatabaseMapper = createSQLSession.getMapper(AmexDatabaseMapper::class.java);

    override fun createAGiftCard(giftCardModelRequest: GiftCardModelRequest): GiftCardModelDBResponse {
        var giftCardModelDBResponse = GiftCardModelDBResponse(giftCardModelRequest.company_name,
            giftCardModelRequest.value,
            giftCardModelRequest.points_cost,
            createAnId())

        logger.info("Created: $giftCardModelDBResponse")

        mapper.createAnGiftCard(giftCardModelDBResponse = giftCardModelDBResponse);
        createSQLSession.commit(true)

        return giftCardModelDBResponse
    }

    override fun getGiftCard(id: String): GiftCardModelResponse {
        var anGiftCard = mapper.getAnGiftCard(uuid = id)

        if(anGiftCard == null){
            throw GiftCardIDNotFoundException("Giftcard ID:$id not found")
        } else {
            return GiftCardModelResponse(anGiftCard.uuid,
                anGiftCard.companyname,
                anGiftCard.value,
                anGiftCard.pointcost)
        }
    }

    override fun getGiftCards(id: Array<Int>): List<GiftCardModelResponse> {
        TODO("Not yet implemented")

    }

    override fun getAllGiftCards(): List<GiftCardModelResponse> {
        return mapper.getAllGiftCards()
            .map (this::convertToResponse)
            .toList()
    }

    override fun deleteAnGiftCard(id: String) {
        var anGiftCard = mapper.getAnGiftCard(uuid = id)

        if(anGiftCard == null){
            throw GiftCardIDNotFoundException("Giftcard ID:$id not found")
        } else {
            logger.info("$id deleted.")

            mapper.deleteAnGiftCard(uuid = id)
            createSQLSession.commit(true)
        }
    }

    private fun createAnId() = UUID.randomUUID().toString()

    private fun convertToResponse(giftCardModelDBResponse: GiftCardModelDBResponse): GiftCardModelResponse{
        return GiftCardModelResponse(giftCardModelDBResponse.uuid,
            giftCardModelDBResponse.companyname,
            giftCardModelDBResponse.value,
            giftCardModelDBResponse.pointcost)
    }
}