package org.futurlab.amexexcerise.database

import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.futurlab.amexexcerise.models.GiftCardModelDBResponse

interface AmexDatabaseMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO amexschema.amexgiftcards (id, companyname, value, pointcost, uuid) " +
            "VALUES (nextval('amexschema.amex_seq'), #{companyname}, #{value}, #{pointcost}, #{uuid})")
    fun createAnGiftCard(giftCardModelDBResponse: GiftCardModelDBResponse)

    @Select("select * from amexschema.amexgiftcards WHERE uuid=#{uuid}")
    fun getAnGiftCard(uuid: String): GiftCardModelDBResponse

    @Delete("DELETE FROM amexschema.amexgiftcards WHERE uuid=#{uuid}")
    fun deleteAnGiftCard(uuid: String)

    @Select("select * from amexschema.amexgiftcards")
    fun getAllGiftCards(): List<GiftCardModelDBResponse>
}