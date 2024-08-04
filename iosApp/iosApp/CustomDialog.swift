import Foundation
import SwiftUI


struct CustomDialog: View {
  @Binding var isShowing: Bool
  let content: () -> any View

  var body: some View {
    GeometryReader { geometry in
      if isShowing {
        ZStack {
          Color.black.opacity(isShowing ? 0.5 : 0)
            .edgesIgnoringSafeArea(.all)
            .onTapGesture {
              isShowing = false
            }

          VStack {
            Spacer()
              VStack {
                  AnyView(content())
              }
              .frame(maxWidth: geometry.size.width * 0.8)
              .padding()
              .background(Color.white)
              .cornerRadius(10)
              .shadow(radius: 5)
            Spacer()
          }
        }
      }
    }
  }
}
//
//struct CustomDialogModifier: ViewModifier {
//    @Binding var isShowing: Bool
//    let dialogContent: () -> AnyView
//
//    func body(content: Content) -> some View {
//        ZStack {
//            content
//            CustomDialog(isShowing: $isShowing, content: dialogContent)
//        }
//    }
//}
//
//
//extension View {
//    func customDialog(isShowing: Binding<Bool>, @ViewBuilder content: @escaping () -> AnyView) -> some View {
//        modifier(CustomDialogModifier(isShowing: isShowing, dialogContent: content))
//    }
//}
