//
//  HomeViewModel.swift
//  characterIosApp
//
//  Created by Josué Solís on 2024-09-15.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

class HomeViewModel : ObservableObject {
    @Published var state: HomeState = .None
    @Published  var list: [CharacterEntity] = []
    let api = CharactersDi().getApi()
    
    func dispatch(_ intent: HomeIntent) {
    switch intent {
    case .Load(let page):
        if let page {
            fetch(page: page)
        }
    }
    }

    private func fetch(page: Int32 = 1) {
        api.characters(page: page, filters: nil, completionHandler: {(response, error) in
            DispatchQueue.main.async { [weak self]  in
                guard let self = self else {return}
                if let response {
                    let data = response.rows
                    self.list.append(contentsOf: data)
                    state = HomeState.Success(response.next as? Int32, list)
                } else if let error {
                    state = HomeState.Failure(AppError.NetworkError)
                }
            }
        })
    }
}
