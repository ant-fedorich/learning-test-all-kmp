import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    @State var celsius: Double = 0
    @State var name: String = ""
    @State var isOn: Bool = false
    @State var value: Double = 0.5
    @State var selection: String = "Option 1"
    @State private var stepValue: Int = 1
    @State private var selectedValue: String = "One"
      let values = ["One", "Two", "Three"]




    func makeUIViewController(context: Context) -> UIViewController {
//        MainViewControllerKt.MainViewController()

        MainViewControllerKt.ComposeEntryPointWithUIViewController {
            let swiftUIView = VStack {
                Text("SwiftUI in Compose Multiplatform").font(.title2)
                
                Spacer().frame(height: 20)
                
                HStack {
                    Button(action: {}) {
                        Text("Button1")
                    }
                
                    Button(action: {}) {
                        Text("Button2")
                    }.buttonStyle(BorderedButtonStyle())
                                  
                    Button(action: {}) {
                        Text("Button3")
                    }.buttonStyle(BorderedProminentButtonStyle())
                }

    
                Spacer().frame(height: 20)
                TextField("Enter your name", text: $name)
                    .padding()
                    .background(
                        RoundedRectangle(cornerRadius: 10)
                            .stroke(Color.gray, lineWidth: 1)
                    )
                    .padding()
                
                Toggle("Enable feature", isOn: $isOn).padding()
                Spacer().frame(height: 20)
                
                Stepper("Value: \(stepValue)", value: $stepValue, in: 0...10)
                    .padding()
                
                Picker("Select a value", selection: $selectedValue) {
                    ForEach(values, id: \.self) { value in
                        Text(value)
                    }
                }
                .pickerStyle(SegmentedPickerStyle())
                .padding()
                
                Slider(value: $value, in: 0...1)

                
                
                let options = ["Option 1", "Option 2", "Option 3"]
                Picker("Select an option", selection: $selection) {
                    ForEach(options, id: \.self) {
                        Text($0)
                    }
                }
                .padding()
                
                
                Spacer().frame(height: 20)
                
            }
            return UIHostingController(rootView: swiftUIView)
        }
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}


struct ContentView: View {
    var body: some View {
        ComposeView().ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}
