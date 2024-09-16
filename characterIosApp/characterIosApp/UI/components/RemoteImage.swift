//
//  RemoteImage.swift
//  characterIosApp
//
//  Created by Josué Solís on 2024-09-15.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct RemoteImage: View {
  let url: String
  let placeholder: Image

  var body: some View {
      AsyncImage(url: URL(string: url), content: { image in
          image.resizable()
      }, placeholder: {placeholder})
  }
}
