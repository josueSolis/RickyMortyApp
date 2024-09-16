//
//  CharacterState.swift
//  characterIosApp
//
//  Created by Josué Solís on 2024-09-15.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
enum CharacterState {
    case isLoading, success(CharacterEntity), failure(Error), none
}
