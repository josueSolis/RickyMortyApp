//
//  Fakes.swift
//  characterIosAppTests
//
//  Created by Josué Solís on 2024-09-15.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
@testable import shared

func characterEntity(
    id: String = "",
    name: String = "",
    status: CharacterEnumStatus = .unknown,
    species: String = "",
    gender: String = "",
    type: String = "",
    location: LocationEntity = LocationEntity(id: "1", name: "Earth"),
    origin:  LocationEntity = LocationEntity(id: "1", name: "Earth"),
    image: String = "",
    episodes: [EpisodeEntity] = [],
    created: String? = "") -> CharacterEntity {
    CharacterEntity(id: id, name: name, status: status, species: species, gender: gender, type: type, location: location, origin: origin, image: image, episodes: episodes, created: created)
}
