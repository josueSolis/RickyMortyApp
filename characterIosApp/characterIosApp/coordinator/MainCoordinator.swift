//
//  MainCoordinator.swift
//  characterIosApp
//
//  Created by Josué Solís on 2024-09-14.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

enum Page: String, Identifiable  {
    case Home
    var id: String {
        self.rawValue
    }
}

enum Sheet :  Identifiable {
    case CharacterDetail(String)
    var id: String {
        switch self {
        case .CharacterDetail:
                "CharacterDetail"
         }
    }
}

class MainCoordinator : ObservableObject {
    @Published var currentPage: Page = .Home
    @Published var pageTitle: String = ""
    @Published var path: NavigationPath = NavigationPath()
    @Published var sheet: Sheet?

    func push(_ page: Page) {
        path.append(page)
    }

    func pop() {
        path.removeLast()
    }

    func popToRoot() {
        path.removeLast(path.count)
    }

    func presentSheet(_ sheet: Sheet) {
            self.sheet = sheet
    }
    func dismissCover() {
            self.sheet = nil
    }
    @ViewBuilder
    func build(_ page:  Page) -> some View  {
        switch page {
        case .Home:
            HomeScreen()
        }
    }
    @ViewBuilder
        func buildSheet(_ sheet: Sheet) -> some View {
            switch sheet {
            case .CharacterDetail(let id):
                CharacterScreen(id: id)
            }
        }
}
