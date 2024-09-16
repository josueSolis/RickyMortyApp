//
//  CharacterCard.swift
//  characterIosApp
//
//  Created by Josué Solís on 2024-09-15.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CharacterCard: View {
  let character: CharacterEntity
    private func retrieveEpisodes() -> String {
        return character.episodes.reduce("") { result, episode in
            result +  "\n[\(episode.episode)] \(episode.name)"
        }
    }
  var body: some View {
    VStack(alignment: .leading) {
      ZStack(alignment: .top) {
        RemoteImage(url: character.image ?? "", placeholder: Image(systemName: "photo"))
          .aspectRatio(contentMode: .fit)
          .frame(maxWidth: .infinity, maxHeight: 330)
          .clipped()
      }
      .padding(.top, 8)

      VStack(alignment: .leading, spacing: 8) {
        TextRow(title: "Name:", value: character.name)
        TextRow(title: "Status:", value: character.status.name)
        TextRow(title: "Species:", value: character.species)
        TextRow(title: "Type:", value: character.type)
        TextRow(title: "Gender:", value: character.gender)
        TextRow(title: "Origin:", value: character.origin.name)
        TextRow(title: "Location:", value: character.location.name)
        TextRow(
          title: "Episodes:",
          value: retrieveEpisodes()
        )
      }
      .padding()
    }
    .cornerRadius(8)
  }
}
