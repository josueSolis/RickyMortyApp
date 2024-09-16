//
//  ApiMock.swift
//  characterIosAppTests
//
//  Created by Josué Solís on 2024-09-15.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
@testable import shared

class ApiMock : CharsApi {
    var argumentsSpy: (Dictionary<String, Any>) -> Void = { _ in }
    var characterHandlerMockInjector: (CharacterEntity?, (any Error)?) = (nil, nil)
    var charactersHandlerMockIntector: (CharacterListResponse?, (any Error)?) = (nil, nil)

    func character(id: String, completionHandler: @escaping (CharacterEntity?, (any Error)?) -> Void) {
        argumentsSpy(["id": id])
        completionHandler(characterHandlerMockInjector.0, characterHandlerMockInjector.1)
    }
    
    func characters(page: Int32, filters: CharacterFilters?, completionHandler: @escaping (CharacterListResponse?, (any Error)?) -> Void) {
        argumentsSpy(["page": page, "filters": filters])
        completionHandler(charactersHandlerMockIntector.0, charactersHandlerMockIntector.1)
    }
}
