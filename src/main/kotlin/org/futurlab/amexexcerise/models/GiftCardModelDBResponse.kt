package org.futurlab.amexexcerise.models

import lombok.Data

@Data
class GiftCardModelDBResponse{

    var id: Int = 0
    var companyname : String = ""
    var value: Int = 0
    var pointcost: Int = 0
    var uuid: String = ""

    constructor(){}
    constructor(companyname: String, value: Int, pointcost: Int, uuid: String) {
        this.companyname = companyname
        this.value = value
        this.pointcost = pointcost
        this.uuid = uuid
    }


}
