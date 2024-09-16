//
//  CharacterViewModelTests.swift
//  characterIosAppTests
//
//  Created by Josué Solís on 2024-09-15.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import XCTest
@testable import characterIosApp
@testable import shared

class CharacterViewModelTests: XCTestCase {
    var viewModel : CharacterViewModel? = nil
    var apiMock = ApiMock()
    override func setUp() async throws {
            viewModel = CharacterViewModel(api: apiMock)
    }

    func testGetItemShouldCallCorrectly() async throws {
        let expectedID = "102"
        var obtainedId = ""
        apiMock.argumentsSpy = { arguments in
            obtainedId = arguments["id"] as? String ?? ""
        }
        apiMock.characterHandlerMockInjector = (characterEntity(id:"1", name:"rick"), nil)
        viewModel?.getItem(expectedID)
        XCTAssertEqual(expectedID, obtainedId)
        switch viewModel?.state {
        case .success(let character) :
            XCTAssertEqual(character.id, "1")
            XCTAssertEqual(character.name, "rick")
        default:
            XCTAssert(false)
        }
    }

    func testGetItemShouldHandleError() async throws {
        let expectedID = "12"
        var obtainedId = ""
        apiMock.argumentsSpy = { arguments in
            obtainedId = arguments["id"] as? String ?? ""
        }
        apiMock.characterHandlerMockInjector = (nil, AppError.NetworkError)
        viewModel?.getItem(expectedID)
        XCTAssertEqual(expectedID, obtainedId)
        switch viewModel?.state {
        case .failure(let error) :
            XCTAssertEqual(error as! AppError, AppError.NetworkError)
        default:
            XCTAssert(false)
        }
    }

}

