//
//  CharacterRow.swift
//  characterIosApp
//
//  Created by Josué Solís on 2024-09-15.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CharacterRow: View {
  let character: CharacterEntity
  let onClick: (CharacterEntity) -> Void

  var body: some View {
      HStack(alignment: .lastTextBaseline) {
        RemoteImage(url: character.image ?? "", placeholder: Image(systemName: "photo"))
          .frame(width: 45, height: 45)
          .clipShape(Circle())
        VStack(alignment: .leading) {
          Text(character.name)
            .font(.body)
          Spacer(minLength: 4)
          Text(character.location.name)
            .font(.caption)
        } .frame(maxWidth: .infinity, alignment: .leading)
    }
      .background()
      .onTapGesture {
          onClick(character)
      }
      .padding(4)
    }
}
