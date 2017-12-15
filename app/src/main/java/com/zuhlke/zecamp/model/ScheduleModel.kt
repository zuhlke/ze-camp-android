package com.zuhlke.zecamp.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class EventModel(val name: String)

class ScheduleModel(val events: List<EventModel>)
