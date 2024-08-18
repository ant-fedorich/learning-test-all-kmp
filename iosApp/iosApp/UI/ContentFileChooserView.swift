//
//  ContentFileChooserView.swift
//  iosApp
//
//  Created by eltonio on 11.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import ComposeApp


struct ContentFileChooserView: View {
    @State var fileMd5 = ""
    @State var fileImporterVisible = false

    var body: some View {
        VStack(spacing: 10) {
            if fileMd5 != "" { Text("File's MD5 hash:").font(.title) }
            if fileMd5 != "" { Text(fileMd5) }
            Button(action: {
                fileImporterVisible.toggle()
            }) {
               Text("Choose File")
            }
        }.fileImporter(
             isPresented: $fileImporterVisible,
             allowedContentTypes: [.pdf],
             allowsMultipleSelection: false
         ) { result in
             do {
                 guard let selectedFile: URL = try result.get().first else { return }
                 guard let data = try? Data(contentsOf: selectedFile) else { return }
                 fileMd5 = FileMd5Hasher.shared.hash(nsdata: data)
             } catch {
                 //
             }
         }
    }
}
