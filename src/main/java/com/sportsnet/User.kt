package com.sportsnet

data class Fan(val id: Long?,val name: String,val interests: List<Interest>)

data class Interest(val id: Long?, val name: String)
