import Foundation
import SwiftUI


struct CustomDialog: View {
    @Binding var showDialog: Bool
    @State var fieldText = ""
    @FocusState var isFocused

  var body: some View {
    GeometryReader { geometry in
      if showDialog {
        ZStack {
            Color.black.opacity(showDialog ? 0.5 : 0).ignoresSafeArea(.all, edges: .all)
            .onTapGesture {
                withAnimation {
                    showDialog = false
                }
            }
          VStack {
            Spacer()
              VStack {
                  Text("This is my custom dialog").font(.title3).padding(.bottom, 4)
                  
                  Text("Text text text text text text text text text text text text text")
                      .lineLimit(nil) // Allow unlimited lines
                      .fixedSize(horizontal: false, vertical: true) // Allow vertical expansion
                      .frame(width: 100)
                      .padding(.bottom, 4)
                  
                  TextField("text field", text: $fieldText)
                      .textFieldStyle(.roundedBorder)
                      .font(.title3)
                      .padding(.horizontal, 16)
                      .focused($isFocused)
                  
                  Button("Close") {
                      withAnimation {
                          showDialog = false
                      }
                  }
              }
              .padding()
              .background(Color.white)
              .cornerRadius(10)
              .shadow(radius: 5)
            Spacer()
          }
          .padding(.horizontal, 12)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
      }
    }
  }
}
