//
//  CharacterViewModel.swift
//  characterIosApp
//
//  Created by Josué Solís on 2024-09-15.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class CharacterViewModel : ObservableObject {
    @Published var state: CharacterState = CharacterState.none
    let api: CharsApi
    init(api: CharsApi = CharactersDi().getApi()) {
        self.api = api
    }

    func getItem(_ id: String) {
        api.character(id: id) {[weak self](character,  error) in
            guard let self = self else { return }
            if let character = character {
                self.state = CharacterState.success(character)
            } else if let error = error {
                self.state = CharacterState.failure(error)
            }
        }
    }
}
