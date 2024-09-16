//
//  HomeState.swift
//  characterIosApp
//
//  Created by Josué Solís on 2024-09-15.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

enum HomeState {
    case Loading
    case Failure(AppError)
    case Success(Int32?, [CharacterEntity])
    case None
}
