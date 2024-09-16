//
//  TextRow.swift
//  characterIosApp
//
//  Created by Josué Solís on 2024-09-15.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct TextRow: View {
  let title: String
  let value: String

  var body: some View {
    HStack(alignment: .top) {
      Text(title)
        .fontWeight(.bold)
      Text(value)
    }
  }
}
