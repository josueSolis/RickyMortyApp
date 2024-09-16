//
//  HomeScreen.swift
//  characterIosApp
//
//  Created by Josué Solís on 2024-09-14.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
struct HomeScreen : View {
    @StateObject var viewModel: HomeViewModel = HomeViewModel()
    @EnvironmentObject var coordinator: MainCoordinator
    var body: some View {
        VStack {
            switch viewModel.state {
            case .Loading:
                ProgressView()
            case .Failure(let appError):
                Text("Error de conexión. Toca para refrescar").onTapGesture {
                    viewModel.dispatch(HomeIntent.Load(1))
                }
            case .Success(let next, let characters):
                List(viewModel.list, id: \.id) {character in
                        CharacterRow(character: character, onClick: { character in
                            self.coordinator.presentSheet(Sheet.CharacterDetail(character.id ))
                        }).onAppear {
                            if (characters.last?.id == character.id) {
                                viewModel.dispatch(.Load(next))
                            }
                        }
                }.listStyle(.plain)
            case .None:
                Text("Espere un momento")
            }
        }.onAppear {
            viewModel.dispatch(.Load(1))
        }
    }
}
