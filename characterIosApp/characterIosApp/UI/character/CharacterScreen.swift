//
//  CharacterScreen.swift
//  characterIosApp
//
//  Created by Josué Solís on 2024-09-15.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CharacterScreen: View {
    @StateObject var viewModel: CharacterViewModel = CharacterViewModel()
    let id: String

    var body: some View {
        ScrollView {
            VStack {
                switch viewModel.state {
                case .isLoading:
                    ProgressView()
                case .success(let character):
                    CharacterCard(character: character)
                case .failure(let error):
                    Text("Error fetching character data")
                case .none :
                    Text("Sin datos")
                }
            }
            .padding(16)
        }.onAppear {
            viewModel.getItem(id)
        }
    }
}

